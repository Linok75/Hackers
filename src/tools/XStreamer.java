package tools;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import exceptions.ImpossibleLoadOfXML;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author MyMac
 */
public /*abstract*/ class XStreamer<E> {

    private XStream xstream;

    public XStreamer() {
        this.xstream = new XStream(new StaxDriver());
        xstream.autodetectAnnotations(true);
    }

    public void alias(String s, Class c) {
        this.xstream.alias(s, c);
    }

    public void save(E e, String location) {
        try {
            xstream.toXML(e, new FileWriter(location));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public E load(String location) throws ImpossibleLoadOfXML {
        /*
        String xml = "";
        try {
            InputStream ips = new FileInputStream(location);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            while ((ligne = br.readLine()) != null) {
                xml += ligne + "\n";
            }
            br.close();
            ipsr.close();
            ips.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }*/

        E e;

        try {
            File f = new File(location);
            e = (E) xstream.fromXML(f);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new ImpossibleLoadOfXML();
        }
        //make(e);

        return e;
    }

    //protected abstract void make(E e);

    //Permet de l'utiliser aussi cpmme un xstream normal
//    public String toXml(Object o) {
//        return this.xstream.toXML(o);
//    }
//    public Object fromXML(String xml) {
//        return this.xstream.fromXML(xml);
//    }

}
