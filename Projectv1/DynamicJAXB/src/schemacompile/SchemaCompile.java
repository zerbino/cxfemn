package schemacompile;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.xml.sax.InputSource;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JPackage;
import com.sun.tools.xjc.api.S2JJAXBModel;
import com.sun.tools.xjc.api.SchemaCompiler;
import com.sun.tools.xjc.api.XJC;

public class SchemaCompile {
	private SchemaCompiler sc;
	public SchemaCompile(String xsdFilePath){
		sc = XJC.createSchemaCompiler();
		File file = new File(xsdFilePath);
		sc.parseSchema(new InputSource(file.toURI().toString()));
		S2JJAXBModel model = sc.bind();
		List<JClass> classesToProcess = new ArrayList<JClass>();
		JCodeModel cm = model.generateCode(null, null);
		Iterator<JPackage> packages = cm.packages();
        while (packages.hasNext()) {
            JPackage pkg = packages.next();
            Iterator<JDefinedClass> classes = pkg.classes();
            while (classes.hasNext()) {
                JDefinedClass cls = classes.next();
                classesToProcess.add(cls);
            }
        }
     // Look for Inner Classes and add them
        ArrayList<JDefinedClass> innerClasses = new ArrayList<JDefinedClass>();
        for (int i = 0; i < classesToProcess.size(); i++) {
            innerClasses.addAll(getInnerClasses(classesToProcess.get(i)));
        }
        
        classesToProcess.addAll(innerClasses);
		try {
			cm.build(new File("."));
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Class> cs = new ArrayList<Class>();
		for (JClass c: classesToProcess){
			System.out.println(c.fullName());
			try {
				cs.add(Class.forName(c.fullName()));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
	}
	private HashSet<JDefinedClass> getInnerClasses(JClass jClass) {
        // Check this xjcClass for inner classes.  If one is found, search that one too.

        HashSet<JDefinedClass> classesToReturn = new HashSet<JDefinedClass>();
        Iterator<JDefinedClass> it = ((JDefinedClass) jClass).classes();

        while (it.hasNext()) {
            JDefinedClass innerClass = it.next();
            classesToReturn.add(innerClass);
            classesToReturn.addAll(getInnerClasses(innerClass));
        }

        return classesToReturn;
    }
}
