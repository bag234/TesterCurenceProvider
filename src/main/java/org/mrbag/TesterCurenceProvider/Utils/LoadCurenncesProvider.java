package org.mrbag.TesterCurenceProvider.Utils;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import org.bag.Conveer.Curences.Provider;
import org.bag.Conveer.Curences.Util.TestingProvider;
import org.bag.Conveer.api.fabric.Utils.LoaderProviderUtil;


public class LoadCurenncesProvider {

	LoaderProviderUtil utils;
	
	Set<Class<? super Provider>> listAvable; 
	
	ArrayList<? super Provider> listLoadAvable; 
	
	public LoadCurenncesProvider(String str) {
		 utils = new LoaderProviderUtil(); 
		 listAvable = utils.scanPackage(str);
		 
		 listLoadAvable = new ArrayList<>();
		 
		 listAvable.stream()
				 .map(cl ->{
					 try {
						 return cl.getConstructor(null).newInstance(null);
					} catch (Exception e) {
						return null;
					}
				 })
				 .map(obj -> (Provider) obj)
				 .filter(obj -> obj != null)
				 .collect(Collectors.toSet()).forEach(obj -> listLoadAvable.add(obj));;
	}
	
	public void printListAvvableProvider(PrintStream out) {
		for (int i = 0; i < listLoadAvable.size(); i++) {
			out.printf("%d | %s\n", i, listLoadAvable.get(i).getClass().getSimpleName());
		}
	}
	
	public boolean callProviderByIdAvable(int id, PrintStream out) {
		if (id > -1 && id < listLoadAvable.size())
			TestingProvider.testProvider((Provider) listLoadAvable.get(id), out);
		else {
			out.println("[input error]");
			return false;
		}
		return true;
	}
	
	
	static LoadCurenncesProvider old = new LoadCurenncesProvider("org.bag.Conveer.Curences");
	
	public static LoadCurenncesProvider load() {
		return old;
	}
	
}
