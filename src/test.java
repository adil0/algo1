

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean buy=false;
		System.out.println(getTrendInvInd(buy));
	}
	
	
	
	public static double getTrendInvInd(boolean buy){
	
	double trendRevInd=0;
	int absMaxTrendSignalValue = 50;
    double retMovingAveragesBps = 100; 
    double depthIndBuy= 10;
    double depthIndSell= 10;
    
    if (Math.abs(retMovingAveragesBps) < 1)
    {
    	trendRevInd = 0;
    }
    else if (retMovingAveragesBps > 0)
    {
        trendRevInd = buy ? Math.min(depthIndBuy, Math.min(retMovingAveragesBps, absMaxTrendSignalValue)) : Math.max(-depthIndSell, Math.max(-absMaxTrendSignalValue, -retMovingAveragesBps));
    }
    else if (retMovingAveragesBps < 0)
    {
        trendRevInd = buy ? Math.max(-depthIndBuy,Math.max(-absMaxTrendSignalValue, retMovingAveragesBps)) : Math.min(depthIndSell, Math.min(absMaxTrendSignalValue, -retMovingAveragesBps));
    }
    // trend is reversed
    trendRevInd = (-1) * trendRevInd;
    return trendRevInd;
}

}
