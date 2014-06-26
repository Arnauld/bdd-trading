package trading;

import com.google.common.collect.Lists;
import com.xeiam.xchart.*;
import org.junit.Test;
import trading.util.Collector;
import trading.util.Visitor;

import java.awt.*;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @author Arnauld Loyer (@aloyer)
 */
public class PlotTest {
    @Test
    public void raw() throws IOException {
        Collector<HistoricalQuote> collector = new Collector<HistoricalQuote>();

        HistoricalQuoteCsvReader reader = new HistoricalQuoteCsvReader();
        reader.read(getClass().getResourceAsStream("/AAPL.csv"), collector);

        List<Double> yData0 = Lists.transform(collector.getCollected(), HistoricalQuote.fnAdjClose());
        List<Double> yData = Lists.reverse(yData0);


        // Create Chart
        Chart chart = new Chart(500, 400);
        chart.setChartTitle("Sample Chart");
        chart.setXAxisTitle("X");
        chart.setYAxisTitle("Y");
        StyleManager styleManager = chart.getStyleManager();
        styleManager.setChartType(StyleManager.ChartType.Area);
        styleManager.setPlotBackgroundColor(Color.WHITE);
        styleManager.setChartBackgroundColor(Color.WHITE);

        Series series = chart.addSeries("y(x)", null, yData);
        series.setMarker(SeriesMarker.NONE);
        series.setLineStyle(SeriesLineStyle.SOLID);
        series.setLineColor(SeriesColor.ORANGE);

        BitmapEncoder.savePNG(chart, "./PlotTest_raw.png");
        BitmapEncoder.savePNGWithDPI(chart, "./PlotTest_raw_300_DPI.png", 300);
        BitmapEncoder.saveJPG(chart, "./PlotTest_raw.jpg", 0.95f);
    }
}
