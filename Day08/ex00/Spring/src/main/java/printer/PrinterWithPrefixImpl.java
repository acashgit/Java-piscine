package printer;

import renderer.Renderer;

public class PrinterWithPrefixImpl implements Printer{
    private Renderer renderer;
    private String prefix;

    public PrinterWithPrefixImpl(Renderer renderer, String prefix){
        this.renderer = renderer;
        prefix = prefix;
    }

    public PrinterWithPrefixImpl(Renderer renderer){
        this.renderer = renderer;
        prefix = "";
    }

    public void setPrefix(String prefix){
        this.prefix = prefix;
    }
    @Override
    public void print(String text){
        renderer.print(prefix + "" + text);
    }
}
