package com.rjxy.agriculture.ui.fragment;


import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import com.rjxy.agriculture.bean.ChartPagerBean;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;


public class ChartView {
	
	public void chartView(Context context, ChartPagerBean chartPagerBean,
			LinearLayout layout) {
	
		ChartPagerBean chart  = chartPagerBean;
		XYMultipleSeriesDataset mDataSet = new XYMultipleSeriesDataset();
		
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		mRenderer.setApplyBackgroundColor(false);
		mRenderer.setAxisTitleTextSize(16);
		mRenderer.setChartTitle(chart.majorValueName);
		mRenderer.setChartTitleTextSize(25);
		mRenderer.setLabelsTextSize(16);
		mRenderer.setPointSize(13);
		mRenderer.setXAxisMax(10);
		mRenderer.setBackgroundColor(Color.WHITE);
		mRenderer.setMarginsColor(Color.argb(0x00, 0x01, 0x01, 0x01));
		mRenderer.setXAxisMin(0);
		mRenderer.setYAxisMax(chart.majorValueMax);
		mRenderer.setYAxisMin(chart.majorValueMin);
		mRenderer.setZoomButtonsVisible(true);
		
		XYSeriesRenderer sr = new XYSeriesRenderer();
		sr.setPointStyle(PointStyle.CIRCLE);
		sr.setColor(Color.BLUE);
		sr.setFillPoints(true);
		
		mRenderer.addSeriesRenderer(sr);

		XYSeries series = new XYSeries(chart.majorValueName);
		int i=1;
		while(i<chart.majorValue.size()){
			series.add(i, chart.majorValue.get(i));
			i++;
		}
		mDataSet.addSeries(series);

		GraphicalView lineChartView = ChartFactory.getLineChartView(context,
				mDataSet, mRenderer);//折线图
		layout.removeAllViews();
		layout.addView(lineChartView, new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		lineChartView.repaint();
	}
}
