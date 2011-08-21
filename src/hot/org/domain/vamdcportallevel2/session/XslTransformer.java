package org.domain.vamdcportallevel2.session;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.io.*;

/** Creates an XSLT transformer for processing an XML document.
 *  A new transformer, along with a style template are created
 *  for each document transformation. The XSLT, DOM, and
 *  SAX processors are based on system default parameters.
 */

public class XslTransformer {
  private TransformerFactory factory;

  public XslTransformer() {
    factory =  TransformerFactory.newInstance();
  }

  /** Transform an XML and XSL document as <code>Reader</code>s,
   *  placing the resulting transformed document in a
   *  <code>Writer</code>. Convenient for handling an XML
   *  document as a String (<code>StringReader</code>) residing
	in memory, not on disk. The output document could easily
	be
   *  handled as a String (<code>StringWriter</code>) or as a
   *  <code>JSPWriter</code> in a JavaServer page.
   */

  public void process(Reader xmlFile, Reader xslFile,
                      Writer output)
                throws TransformerException {
    process(new StreamSource(xmlFile),
            new StreamSource(xslFile),
            new StreamResult(output));
  }

  /** Transform an XML and XSL document as <code>File</code>s,
   *  placing the resulting transformed document in a
   *  <code>Writer</code>. The output document could easily
   *  be handled as a String (<code>StringWriter</code)> or as
   *  a <code>JSPWriter</code> in a JavaServer page.
   */
  public void process(File xmlFile, File xslFile,
                      Writer output)
                throws TransformerException {
    process(new StreamSource(xmlFile),
            new StreamSource(xslFile),
            new StreamResult(output));
  }

  /** Transform an XML <code>File</code> based on an XSL
   *  <code>File</code>, placing the resulting transformed
   *  document in a <code>OutputStream</code>. Convenient for
   *  handling the result as a <code>FileOutputStream</code> or
   *  <code>ByteArrayOutputStream</code>.
   */

  public void process(File xmlFile, File xslFile,
                      OutputStream out)
                 throws TransformerException {
    process(new StreamSource(xmlFile),
            new StreamSource(xslFile),
            new StreamResult(out));
  }

  /** Transform an XML source using XSLT based on a new template
   *  for the source XSL document. The resulting transformed
   *  document is placed in the passed in <code>Result</code>
   *  object.
   */

  public void process(Source xml, Source xsl, Result result)
                throws TransformerException {
    try {
      Templates template = factory.newTemplates(xsl);
      Transformer transformer = template.newTransformer();
      transformer.transform(xml, result);
    } catch(TransformerConfigurationException tce) {
        throw new TransformerException(
                    tce.getMessageAndLocation());
    } catch (TransformerException te) {
      throw new TransformerException(
                  te.getMessageAndLocation());
    }
  }
}
