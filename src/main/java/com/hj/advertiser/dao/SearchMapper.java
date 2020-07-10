package com.hj.advertiser.dao;

import com.hj.advertiser.model.SearchAction;
import com.hj.advertiser.model.SearchCenter;
import com.hj.advertiser.model.SearchKeywords;
import com.hj.advertiser.model.SearchResult;

public interface SearchMapper {
	
	public int insertSearchCenter(SearchCenter center);
	
	public int updateSearchCenterSelective(SearchCenter center);
	
	public int insertSearchKeywords(SearchKeywords keywords);
	
	public int updateSearchKeywordsSelective(SearchKeywords keywords);
	
	public int insertSearchAction(SearchAction action);
	
	public int insertSearchResult(SearchResult result);

}
