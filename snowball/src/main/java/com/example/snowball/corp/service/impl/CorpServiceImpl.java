package com.example.snowball.corp.service.impl;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.example.snowball.corp.service.CorpService;
import com.example.snowball.corp.service.vo.CorpSearchVo;
import com.example.snowball.corp.service.vo.CorpVo;

@Service
public class CorpServiceImpl implements CorpService {
	@Autowired
	private CorpDao corpDao;

	@Override
	public int insert(CorpVo corpVo) throws Exception {
		return corpDao.insert(corpVo);
	}

	@Override
	public int delete() throws Exception {
		return corpDao.delete();
	}

	@Override
	public int txInsert() throws Exception {
		/*
		 * URL url = new URL(
		 * "http://opendart.fss.or.kr/api/corpCode.xml?crtfc_key=5c97df1a4695eb42eab1cc3545d480c18365c2f2"
		 * );
		 * 
		 * InputStream inputStream = url.openConnection().getInputStream();
		 * ZipInputStream zipInputStream = new ZipInputStream(inputStream); ZipEntry
		 * zipEntry = zipInputStream.getNextEntry();
		 * 
		 * FileOutputStream fileOutputStream = new FileOutputStream("D:\\temp\\" +
		 * zipEntry.getName());
		 * 
		 * int length = 0; byte[] buffer = new byte[256];
		 * 
		 * while ((length = zipInputStream.read(buffer)) != -1) {
		 * fileOutputStream.write(buffer, 0, length); }
		 * 
		 * zipInputStream.closeEntry(); fileOutputStream.flush();
		 * fileOutputStream.close(); zipInputStream.close();
		 */

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		Document document = documentBuilder.parse("D:\\temp\\CORPCODE.xml");

		NodeList list = document.getElementsByTagName("list");

		int count = 0;
		CorpVo corpVo = null;
		for (int i = 0, listLength = list.getLength(); i < listLength; i++) {
			NodeList childList = list.item(i).getChildNodes();
			corpVo = new CorpVo();
			// System.out.println("===============================시작============================");
			for (int j = 0, childLength = childList.getLength(); j < childLength; j++) {
				Node child = childList.item(j);
				String childNm = child.getNodeName();
				// System.out.println(childNm + " : " + child.getTextContent());
				if ("corp_code".equals(childNm)) {
					corpVo.setCorpCode(child.getTextContent());
				} else if ("corp_name".equals(childNm)) {
					corpVo.setCorpName(child.getTextContent());
				} else if ("stock_code".equals(childNm)) {
					corpVo.setStockCode(child.getTextContent());
				} else if ("modify_date".equals(childNm)) {
					corpVo.setModifyDate(child.getTextContent());
				}

			}

			if (corpVo.getStockCode() == null || corpVo.getStockCode().length() != 6) {
				continue;
			}

			insert(corpVo);
			count++;
		}

		return count;
	}

	@Override
	public List<CorpVo> list(CorpSearchVo corpSearchVo) throws Exception {
		return corpDao.list(corpSearchVo);
	}

}
