package me.thinkjet.interceptor;

import java.util.HashMap;
import java.util.Map;

import me.thinkjet.model.JobRecord;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

public class JobRecordInterceptor implements Interceptor{
	public static Map<Integer,Integer> record=new HashMap<Integer,Integer>();
	public static int count=0;
	public void intercept(ActionInvocation ai) {
	   count++;
		int id=Integer.parseInt(ai.getController().getPara("id"));
		if(record.get(id)==null)
		    record.put(id,1);
		else {
			record.put(id, (record.get(id)+1));
		if(count>100) {
					Object key[] = record.keySet().toArray();
					Object value[]=record.values().toArray();
					for(int b=0;b<record.size();b++){
						JobRecord j=JobRecord.dao.findFirst("select * from jobrecord where id ="+key[b]);
						if(j!=null)
						{
							j.set("views",(j.getInt("views")+value[b].hashCode()));
							j.update();
						}
						else {
							j=new JobRecord();
							j.set("id",key[b]);
							j.set("views", value[b]);
							j.set("comments",0);
							j.save();
						}

					}
					record.clear();
				 count=0;
			}
	}
ai.invoke();
	}
}
