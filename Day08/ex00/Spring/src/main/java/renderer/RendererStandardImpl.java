package renderer;

import processor.PreProcessor;

public class RendererStandardImpl implements Renderer{
    PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }
    @Override
    public void print(String text) {
        System.out.println(preProcessor.process(text));
    }
}
