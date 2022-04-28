package printer;

import renderer.Renderer;

import java.time.LocalDateTime;
import java.util.Date;

public class PrinterWithDateTimeImpl implements Printer{
    Renderer renderer;
    public PrinterWithDateTimeImpl(Renderer renderer){
        this.renderer = renderer;
    }

    public void print(String text){
        Date time = new Date();
        renderer.print(time + " " + text);
    }
}
