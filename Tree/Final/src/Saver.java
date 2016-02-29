import java.io.*;
import java.util.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.w3c.dom.*;

public class Saver {

	private static void saveNode(TNode node, Element parent, Document dom) {
		Element e = null;
		Element et = null;
		Element ec = null;
		Element er = null;
		Element el = null;
		
		e = dom.createElement("Node");
		System.out.println("Node");
		et = dom.createElement("Type");
		et.appendChild(dom.createTextNode(node.type));
		System.out.println(node.type);
		e.appendChild(et);
		
		ec = dom.createElement("Content");
		ec.appendChild(dom.createTextNode(node.content));
		System.out.println(node.content);
		e.appendChild(ec);
		
		if(node.childLeft != null) {
			el = dom.createElement("LeftNode");
			System.out.println("LeftNode");
			saveNode(node.childLeft, el, dom);
			e.appendChild(el);
		}
		
		if(node.childRight != null) {
			er = dom.createElement("RightNode");
			System.out.println("RightNode");
			saveNode(node.childRight, er, dom);
			e.appendChild(er);
		}
		
		parent.appendChild(e);
	}
	
	//takes the parent node
	//direction from that it is going right left or both
	//
	private static void buildNode(TNode node, NodeList nList, int i, int direction, boolean isRoot) {
		System.out.println("buildNode");
		NodeList cList = nList.item(i).getChildNodes();
		Node nodeNode = nList.item(i);
		TNode a = new TNode("","");
		
		for(int j = 0; j < cList.getLength(); j++) {
			Node nNode = cList.item(j);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {					
				Element e = (Element)nNode;
						
				//found the type or content string
				if(!e.getTagName().contains("Node")) {
					if(e.getTagName().contains("Type")) {
						a.setType(nNode.getTextContent());
						System.out.println("TYPE " + nNode.getTextContent());
					}
					else {
						a.setString(nNode.getTextContent());
						System.out.println("String " + nNode.getTextContent());
					}
				}
				else {
					//direction check
					int dir = direction;
					if(e.getTagName().contains("Right")) {
						dir = 1;
					}
					else if(e.getTagName().contains("Left")) {
						dir = 0;
					}
						
					if(e.getTagName().equals("Node")) {
						System.out.println("Node Recursion: " + node.getString() + "---");	
						buildNode(node, cList, j, dir, false);
					}
					else {
						System.out.println("DirNode Recursion");
						if(!isRoot)
							buildNode(a, cList, j, dir, false);
						else
							buildNode(node, cList, j, dir, false);
					}
				}
			}
		}
		
		if(!a.getString().equals("") && !isRoot) {
			if(direction == 0 || direction == 2){
				node.setLeft(a);
			}
			else if (direction == 1 || direction == 2){
				node.setRight(a);
			}
		}
		
	}
	
	public static void save(TNodeList myList) {
		Document dom;
		Element e = null;
		Element e2 = null;

		// instance of a DocumentBuilderFactory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			// use factory to get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			// create instance of DOM
			dom = db.newDocument();

			// create the root element
			Element rootEle = dom.createElement("CelebTree");

			// create data elements and place them under root
			TNode node = myList.getRoot();
			
			saveNode(node, rootEle, dom);

			dom.appendChild(rootEle);

			try {
				Transformer tr = TransformerFactory.newInstance().newTransformer();
				tr.setOutputProperty(OutputKeys.INDENT, "yes");
				tr.setOutputProperty(OutputKeys.METHOD, "xml");
				tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				//tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
				tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

				// send DOM to file
				tr.transform(new DOMSource(dom), 
									 new StreamResult(new FileOutputStream("save.xml")));

			} catch (TransformerException te) {
				System.out.println(te.getMessage());
			} catch (IOException ioe) {
				System.out.println(ioe.getMessage());
			}
		} catch (ParserConfigurationException pce) {
			System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
		}
	
	}
	
	public static TNodeList load() {
        TNodeList rnl = new TNodeList();
	TNode root = rnl.getRoot();
		
	Document dom;
        // Make an  instance of the DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use the factory to take an instance of the document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // parse using the builder to get the DOM mapping of the    
            // XML file
            dom = db.parse("save.xml");

            Element doc = dom.getDocumentElement();
			
			System.out.println(doc.getNodeName());
			
			NodeList nList = doc.getElementsByTagName("Node");
			
			System.out.println("----------------------------");
			
			nList = doc.getChildNodes();
			
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				if(nNode.getNodeName().equals("Node")) {
					buildNode(root, nList, i, 2, true);
				}
			}

        } catch (ParserConfigurationException pce) {
            System.out.println(pce.getMessage());
        } catch (SAXException se) {
            System.out.println(se.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        	return rnl;
	}

	//private static void addNodes(Document dom)
}