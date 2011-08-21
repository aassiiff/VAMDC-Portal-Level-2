package org.domain.vamdcportallevel2.session;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aakram1
 */
public class TAPHTTPGet implements Runnable {

    private SocketChannel server = null; // Channel for reading from server
    private FileOutputStream outputStream = null; // Stream to destination file
    private WritableByteChannel destination; // Channel to write to it
    private URI uri = null;
    private String queryURI = null;
    private String hostname = null;
    private int port = -1;
    private String query = null;
    private String scheme = null;
    private String path = null;
    private UUID uuid = null;
    // counter -99 means only one Query is submitted
    private int counter = -99;
    private boolean done = false;

    public boolean isDone() {
        return done;
    }

    public TAPHTTPGet(String queryURIValue) {
        this.queryURI = queryURIValue;
        generateUUID();
        parseQueryURI();
    }

    public TAPHTTPGet(String queryURIValue, UUID uuidValue) {
        this.queryURI = queryURIValue;
        this.uuid = uuidValue;
        parseQueryURI();
    }

    public TAPHTTPGet(String queryURIValue, UUID uuidValue, int counterValue) {
        this.queryURI = queryURIValue;
        this.uuid = uuidValue;
        this.counter = counterValue;
        parseQueryURI();
    }

    private void generateUUID() {
        uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        System.out.println("Random UUID String = " + randomUUIDString);
        System.out.println("UUID version       = " + uuid.version());
        System.out.println("UUID variant       = " + uuid.variant());
    }

    private void parseQueryURI() {
        try {
            uri = new URI(queryURI);

            // Now query and verify the various parts of the URI
            scheme = uri.getScheme();
            if (scheme == null || !scheme.equals("http")) {
                throw new IllegalArgumentException("Must use 'http:' protocol");
            }

            System.out.println(scheme);

            hostname = uri.getHost();
            System.out.println(hostname);

            port = uri.getPort();
            if (port == -1) {
                port = 80; // Use default port if none specified
            }
            System.out.println(port);

            path = uri.getRawPath();
            if (path == null || path.length() == 0) {
                path = "/";
            }
            System.out.println(path);

            query = uri.getRawQuery();
            query = (query == null) ? "" : '?' + query;
            System.out.println(query);

        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
    }

    private void initiateConnection() {
        // line
        // indicates
        // end of
        // request
        // headers

        try {
            // Combine the hostname and port into a single address object.
            // java.net.SocketAddress and InetSocketAddress are new in Java 1.4
            SocketAddress serverAddress = new InetSocketAddress(hostname, port);
            // Open a SocketChannel to the server
            server = SocketChannel.open(serverAddress);
            // Put together the HTTP request we'll send to the server.
            String request = "GET " + path + query + " HTTP/1.1\r\n" + "Host: " + hostname + "\r\n" + "Connection: close\r\n" + "User-Agent: " + TAPHTTPGet.class.getName() + "\r\n" + "\r\n"; // Blank
            // line
            // indicates
            // end of
            // request
            // headers
            // Now wrap a CharBuffer around that request string
            CharBuffer requestChars = CharBuffer.wrap(request);
            // Get a Charset object to encode the char buffer into bytes
            Charset charset = Charset.forName("ISO-8859-1");
            // Use the charset to encode the request into a byte buffer
            ByteBuffer requestBytes = charset.encode(requestChars);
            // Finally, we can send this HTTP request to the server.
            server.write(requestBytes);
            
            if(counter == -99){
            	outputStream = new FileOutputStream("/opt/queryResults/" + uuid + ".xml");
            }else {
            	outputStream = new FileOutputStream("/opt/queryResults/" + uuid + "-" + counter + ".xml");
            }
            destination = outputStream.getChannel();

        } catch (IOException ex) {
            Logger.getLogger(TAPHTTPGet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void run() {
        initiateConnection();
        // Allocate a 32 Kilobyte byte buffer for reading the response.
        // Hopefully we'll get a low-level "direct" buffer
        ByteBuffer data = ByteBuffer.allocateDirect(32 * 1024);

        // Have we discarded the HTTP response headers yet?
        boolean skippedHeaders = false;
        // The code sent by the server
        int responseCode = -1;

        // Now loop, reading data from the server channel and writing it
        // to the destination channel until the server indicates that it
        // has no more data.
        try {
            while (server.read(data) != -1) { // Read data, and check for end


                // Read data, and check for end
                data.flip(); // Prepare to extract data from buffer
                // All HTTP reponses begin with a set of HTTP headers, which
                // we need to discard. The headers end with the string
                // "\r\n\r\n", or the bytes 13,10,13,10. If we haven't already
                // skipped them then do so now.
                if (!skippedHeaders) {
                    // First, though, read the HTTP response code.
                    // Assume that we get the complete first line of the
                    // response when the first read() call returns. Assume also
                    // that the first 9 bytes are the ASCII characters
                    // "HTTP/1.1 ", and that the response code is the ASCII
                    // characters in the following three bytes.
                    if (responseCode == -1) {
                        responseCode = 100 * (data.get(9) - '0') + 10 * (data.get(10) - '0') + 1 * (data.get(11) - '0');
                        // If there was an error, report it and quit
                        // Note that we do not handle redirect responses.
                        if (responseCode < 200 || responseCode >= 300) {
                            System.err.println("HTTP Error: " + responseCode);
                            System.exit(1);
                        }
                    }
                    // Now skip the rest of the headers.
                    try {
                        for (;;) {
                            if ((data.get() == 13) && (data.get() == 10) && (data.get() == 13) && (data.get() == 10)) {
                                skippedHeaders = true;
                                break;
                            }
                        }
                    } catch (BufferUnderflowException e) {
                        // If we arrive here, it means we reached the end of
                        // the buffer and didn't find the end of the headers.
                        // There is a chance that the last 1, 2, or 3 bytes in
                        // the buffer were the beginning of the \r\n\r\n
                        // sequence, so back up a bit.
                        data.position(data.position() - 3);
                        // Now discard the headers we have read
                        data.compact();
                        // And go read more data from the server.
                        continue;
                    }
                }


                Charset charset = Charset.forName("UTF-8");
                CharsetEncoder encoder = charset.newEncoder();
                

                // Byte Buffer is converted into Byte array
                byte[] bytearray = new byte[data.remaining()];
                data.get(bytearray);

                // Byte Array is converted into String using UTF-8 encoding
                String dataString = new String(bytearray,"UTF-8");
                

                // String is converted back into Byte Buffer
                data = null;
                data = encoder.encode(CharBuffer.wrap(dataString));

                // Write the data out; drain the buffer fully.
                while (data.hasRemaining()) {
                    destination.write(data);
                }
                // Now that the buffer is drained, put it into fill mode
                // in preparation for reading more data into it.
                data.clear(); // data.compact() also works here
                this.done = true;
            }
        } catch (IOException ex) {
            Logger.getLogger(TAPHTTPGet.class.getName()).log(Level.SEVERE, null, ex);
        } finally { // Close the channels and output file stream, if needed
            try {
                if (server != null && server.isOpen()) {
                    server.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                this.done = true;
                Logger.getLogger(TAPHTTPGet.class.getName()).log(Level.INFO, null, "Exiting Finally{}");
            } catch (IOException e) {
            }
        }
        this.done = true;
        Logger.getLogger(TAPHTTPGet.class.getName()).log(Level.INFO, null, "Exiting run()");
        
    }
}
