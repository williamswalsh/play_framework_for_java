package controllers;

import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Http.Context;
import utils.ExceptionMailer;
import play.mvc.SimpleResult;

public class CatchAction extends Action<Catch> {

	@Override
	public Promise<SimpleResult> call(Context ctx) {
		try {
			// This calls the action method which potentially can throw an exception
			return delegate.call(ctx);
		} catch(Throwable e) {
			if(configuration.send()) {
				ExceptionMailer.send(e);
			}
			throw new RuntimeException(e);
		}
	}
}
