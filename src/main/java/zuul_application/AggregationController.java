package zuul_application;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.google.common.collect.Maps;

import rx.Observable;
import rx.Observer;

@RestController
public class AggregationController {

	@Autowired
	private AggregationService aggregationService;
	
	@GetMapping("/aggregate/{id}")
	public DeferredResult<HashMap<String,User>> aggregate(@PathVariable Long id) {
		Observable<HashMap<String, User>> resultObservable = Observable.zip(this.aggregationService.getUserById(id),this.aggregationService.getMovieUserById(id)
				,(user,movieUser) -> {
					HashMap<String,User> map = Maps.newHashMap();
					map.put("user",user);
					map.put("movieUser",movieUser);
					return map;
				});
		
		DeferredResult<HashMap<String,User>> result = new DeferredResult<>();
		resultObservable.subscribe(new Observer<HashMap<String, User>>(){

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onError(Throwable e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNext(HashMap<String, User> t) {
				result.setResult(t);
				
			}
			
		});
		return result;
	}
}
