/*
 * FreeOTP
 *
 * Authors: Nathaniel McCallum <npmccallum@redhat.com>
 *
 * Copyright (C) 2013  Nathaniel McCallum, Red Hat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.fedorahosted.freeotp;

import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class AboutDialogFragment extends BaseAlertDialogFragment {
	public static final String FRAGMENT_TAG = "fragment_about";

	public AboutDialogFragment() {
		super(R.string.about, R.layout.about, 0, android.R.string.ok, 0);
	}

	@Override
	protected void onViewInflated(View view) {
		Resources res = getActivity().getResources();
		TextView tv;

		try {
			PackageManager pm = getActivity().getPackageManager();
			PackageInfo info = pm.getPackageInfo(getActivity().getPackageName(), 0);
			String version = res.getString(R.string.about_version,
                                           info.versionName,
                                           info.versionCode);
			tv = (TextView) view.findViewById(R.id.about_version);
			tv.setText(version);
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}

		String apache2 = res.getString(R.string.link_apache2);
		String license = res.getString(R.string.about_license, apache2);
		tv = (TextView) view.findViewById(R.id.about_license);
		tv.setMovementMethod(LinkMovementMethod.getInstance());
		tv.setText(Html.fromHtml(license));

		String lwebsite = res.getString(R.string.link_website);
		String swebsite = res.getString(R.string.about_website, lwebsite);
		tv = (TextView) view.findViewById(R.id.about_website);
		tv.setMovementMethod(LinkMovementMethod.getInstance());
		tv.setText(Html.fromHtml(swebsite));

		String problem = res.getString(R.string.link_report_a_problem);
		String help = res.getString(R.string.link_ask_for_help);
		String feedback = res.getString(R.string.about_feedback, problem, help);
		tv = (TextView) view.findViewById(R.id.about_feedback);
		tv.setMovementMethod(LinkMovementMethod.getInstance());
		tv.setText(Html.fromHtml(feedback));
	}

	@Override
	public void onStart() {
		super.onStart();

		Resources res = getActivity().getResources();
		String title = res.getString(R.string.about_title,
				res.getString(R.string.app_name));
		getDialog().setTitle(title);
	}

	@Override
	public void onClick(DialogInterface arg0, int arg1) {
	}
}
