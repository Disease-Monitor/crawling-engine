package com.toy.project.crawling.engine;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestCrawling {
	public static final String MAIN_URL = "https://www.corriere.it/politica/";
	public static final String VALID_ARTICLE_CRITERION = "www.corriere.it/politica";
	public static final String VALID_ARTICLE_EXTENSION = "shtml";
	
	public static void main(String[] args) throws IOException {
		Document doc = getDocumentFromUrl(MAIN_URL);
//		System.out.println(doc);
		
		Elements elements = getElementsFromDocument(doc, "a");
//		System.out.println(elements);
		
		Set hrefSet = getAttrributeFromElements(elements, "href");
//		System.out.println(hrefSet);
		
		Set validHrefSet = getValidArticleFromHrefSet(hrefSet, VALID_ARTICLE_CRITERION, VALID_ARTICLE_EXTENSION);
		System.out.println(validHrefSet);
	}
	
	public static Document getDocumentFromUrl(String url) throws IOException {
//		Document doc = Jsoup.connect(url)
//							.data("query", "Java")
//							.userAgent("Mozilla")
//							.cookie("auth", "token")
//							.timeout(3000)
//							.post();
		
		Document doc = Jsoup.connect(url)
							.get();
		
		return doc;
	}
	
	public static Elements getElementsFromDocument(Document doc, String tag) {
		Elements elements = doc.getElementsByTag(tag);
		
		return elements;
	}
	
	public static Set getAttrributeFromElements(Elements elements, String attr) {
		Set hrefSet = new HashSet<String>();
		
		for(Element element: elements) {
			hrefSet.add(element.attr(attr));
		}
		
		return hrefSet;
	}
	
	public static Set getValidArticleFromHrefSet(Set hrefSet, String criterion, String extension) {
		Set validHrefSet = new HashSet<String>();
		
		for(Object href: hrefSet) {
			if(href.toString().contains(criterion) &&
				href.toString().endsWith(extension)) {
				validHrefSet.add(href.toString());
			}
		}
		
		return validHrefSet;
	}
}
