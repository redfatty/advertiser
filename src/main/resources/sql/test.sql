--SELECT * FROM advertiser WHERE bd_uid = '1b993641fa9bc87946e274a8'

--SELECT * FROM search_result WHERE bd_uid = '1b993641fa9bc87946e274a8'
--SELECT * FROM search_action WHERE search_action_id = 16
--SELECT * FROM search_center WHERE search_center_id = 12

SELECT
	ad.advertiser_id,
	--ad.bd_uid,
	ad."name",
	ad.addr,
	--ad.address_norm,
	ad.city_name,
	ad.area_name,
	t.center_name,t.center_bd_uid
FROM advertiser ad
LEFT JOIN (
	-- 一个广告商可以归属于多个中心点, 取距离最近的那一个
	
	
	SELECT temp.* FROM
		(
			SELECT 
				 sc.center_addr,sc.center_bd_uid,sc.center_name, sr.*,
				 ROW_NUMBER() OVER (PARTITION BY sr.bd_uid ORDER BY sr.distance) AS rownum
			FROM search_result sr
			INNER JOIN search_action sa ON sa.search_action_id = sr.search_action_id
			INNER JOIN search_center sc ON sc.search_center_id = sa.search_center_id
		) temp
	WHERE rownum = 1
) t ON t.bd_uid = ad.bd_uid
--WHERE ad.bd_uid = '1b993641fa9bc87946e274a8'
ORDER BY 
	ad.city_code, ad.area_code, 
	t.center_bd_uid, ad.advertiser_id