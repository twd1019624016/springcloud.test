package elasticJob;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

public class MyElasticJob implements SimpleJob {
    
    @Override
    public void execute(ShardingContext context) {
        switch (context.getShardingItem()) {
            case 0: 
                System.err.println("分片0-----------"+context.getShardingItem());
                break;
            case 1: 
            	 System.err.println("分片1-----------"+context.getShardingItem());
                break;
            case 2: 
            	System.err.println("分片2-----------"+context.getShardingItem());
                break;
            // case n: ...
            default:
                System.err.println("分片其他-----------"+context.getShardingItem());
        }
    }
}