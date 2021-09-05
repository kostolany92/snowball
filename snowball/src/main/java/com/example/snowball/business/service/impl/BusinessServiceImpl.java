package com.example.snowball.business.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.snowball.business.service.BusinessService;
import com.example.snowball.business.service.vo.BusinessSearchVo;
import com.example.snowball.business.service.vo.BusinessVo;

@Service
public class BusinessServiceImpl implements BusinessService {

	@Autowired
	private BusinessDao businessDao;

	@Override
	public List<BusinessVo> listContent(BusinessSearchVo businessSearchVo) throws Exception {

		// 1. DB 조회
		List<BusinessVo> listContent = businessDao.listContent(businessSearchVo);

		// 2. 없으면 새로 조회후 DB INSERT
		if (listContent == null || listContent.size() == 0) {
			// 사업보고서 리스트 조회
			// rcpNo, year, reportType 추출
			URL url = new URL(
					"http://dart.fss.or.kr/dsab007/detailSearch.ax?maxResults=100&sort=date&series=desc&textCrpNm=%EC%97%94%ED%94%BC%EC%94%A8&startDate=20160901&endDate=20210901&finalReport=recent&publicType=A001&publicType=A002&publicType=A003");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}

			Document doc = Jsoup.parse(sb.toString());
			Elements aList = doc.getElementsByTag("a");
			for (Element a : aList) {
				BusinessVo businessVo = new BusinessVo();
				String html = a.toString();

				// rcpNo 추출
				String rcpNo = null;
				int rcpNoIndex = html.indexOf("rcpNo=");
				if (rcpNoIndex > -1) {
					rcpNo = html.substring(rcpNoIndex + 6, rcpNoIndex + 20);
				} else {
					continue;
				}

				// year, reportType 추출
				String text = a.text();
				String year = null;
				String month = null;
				String reportType = null;
				int dotIndex = text.indexOf(".");
				if (dotIndex > -1) {
					year = text.substring(dotIndex - 4, dotIndex);
					month = text.substring(dotIndex + 1, dotIndex + 3);

					if ("03".equals(month)) {
						reportType = "1";
					} else if ("06".equals(month)) {
						reportType = "2";
					} else if ("09".equals(month)) {
						reportType = "3";
					} else if ("12".equals(month)) {
						reportType = "4";
					}
				}

				// rcpNo로 dcmNo 추출
				url = new URL("http://dart.fss.or.kr/dsaf001/main.do?rcpNo=" + rcpNo);
				con = (HttpURLConnection) url.openConnection();

				con.setRequestMethod("GET");

				in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

				line = null;
				sb = new StringBuilder();
				while ((line = in.readLine()) != null) {
					sb.append(line);
				}

				doc = Jsoup.parse(sb.toString());

				Elements btnDown = doc.getElementsByClass("btnDown");

				String dcmNo = null;
				html = btnDown.toString();
				int dcmNoIndex = html.indexOf(", '");
				if (dcmNoIndex > -1) {
					dcmNo = html.substring(dcmNoIndex + 3, dcmNoIndex + 10);
				}

				// 사업의내용 추출
				url = new URL("http://dart.fss.or.kr/report/viewer.do?rcpNo=" + rcpNo + "&dcmNo=" + dcmNo
						+ "&eleId=9&offset=58760&length=70999&dtd=dart3.xsd");
				con = (HttpURLConnection) url.openConnection();

				con.setRequestMethod("GET");

				in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

				line = null;
				sb = new StringBuilder();
				while ((line = in.readLine()) != null) {
					sb.append(line);
				}

				doc = Jsoup.parse(sb.toString());
				String content = doc.toString();
				if (content.indexOf("II. 사업의 내용") == -1) {
					url = new URL("http://dart.fss.or.kr/report/viewer.do?rcpNo=" + rcpNo + "&dcmNo=" + dcmNo
							+ "&eleId=10&offset=58760&length=70999&dtd=dart3.xsd");
					con = (HttpURLConnection) url.openConnection();

					con.setRequestMethod("GET");

					in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

					line = null;
					sb = new StringBuilder();
					while ((line = in.readLine()) != null) {
						sb.append(line);
					}

					doc = Jsoup.parse(sb.toString());
					content = doc.toString();
				}

				if (content.indexOf("II. 사업의 내용") == -1) {
					url = new URL("http://dart.fss.or.kr/report/viewer.do?rcpNo=" + rcpNo + "&dcmNo=" + dcmNo
							+ "&eleId=11&offset=58760&length=70999&dtd=dart3.xsd");
					con = (HttpURLConnection) url.openConnection();

					con.setRequestMethod("GET");

					in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

					line = null;
					sb = new StringBuilder();
					while ((line = in.readLine()) != null) {
						sb.append(line);
					}

					doc = Jsoup.parse(sb.toString());
					content = doc.toString();
				}

				businessVo.setYear(year);
				businessVo.setReportType(reportType);
				businessVo.setContent(content);

				insertContent(businessVo);
			}
		}
		// 3. 다시 DB 조회

		// 4.

		return listContent;
	}

	@Override
	public int insertContent(BusinessVo businessVo) throws Exception {
		return businessDao.insertContent(businessVo);
	}

	@Override
	public List<BusinessVo> listContentYear(BusinessSearchVo businessSearchVo) throws Exception {
		return businessDao.listContentYear(businessSearchVo);
	}

	@Override
	public List<BusinessVo> listReportTypeList(BusinessSearchVo businessSearchVo) throws Exception {
		return businessDao.listReportTypeList(businessSearchVo);
	}

	@Override
	public String selectContent(BusinessSearchVo businessSearchVo) throws Exception {
		return businessDao.selectContent(businessSearchVo);
	}

}
