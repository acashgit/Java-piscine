package app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import printer.Printer;
import printer.PrinterWithPrefixImpl;
import processor.PreProcessor;
import processor.PreProcessorToUpperImpl;
import renderer.Renderer;
import renderer.RendererErrImpl;

public class Program {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Printer printer = context.getBean("printerWithPrefix", Printer.class);
        printer.print("Hello!") ;
    }
}
