package elasticJob;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobCoreConfiguration.Builder;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

public class JobDemo implements ElasticJobListener{
    
    public static void main(String[] args) {
        new JobScheduler(createRegistryCenter(), createJobConfiguration()).init();
    }
    
    private static CoordinatorRegistryCenter createRegistryCenter() {
        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration("localhost:2181", "elastic-job-demo"));
        regCenter.init();
        return regCenter;
    }
    
    private static LiteJobConfiguration createJobConfiguration() {
    	 // 定义作业核心配置
    	Builder description = JobCoreConfiguration.newBuilder("demoSimpleJob", "0/15 * * * * ?", 1).description("test");
       
        JobCoreConfiguration simpleCoreConfig = description.build();
       
        // 定义SIMPLE类型配置
        System.out.println( MyElasticJob.class.getCanonicalName());
        SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(simpleCoreConfig, MyElasticJob.class.getCanonicalName());
        // 定义Lite作业根配置
        LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).build();
		return simpleJobRootConfig;
    }

	@Override
	public void beforeJobExecuted(ShardingContexts shardingContexts) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterJobExecuted(ShardingContexts shardingContexts) {
		// TODO Auto-generated method stub
		
	}
}