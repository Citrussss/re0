DELIMITER $$
DROP FUNCTION IF EXISTS `getDistance`$$
CREATE  FUNCTION `getDistance`(lat1 decimal ,lng1 decimal,lat2 decimal,lng2 decimal) RETURNS decimal
begin
IF(LENGTH(lat1) =0 || LENGTH(lng1) =0 || LENGTH(lat2) =0 || LENGTH(lng2) =0 || lat1 IS NULL|| lng1 IS NULL|| lat2 IS NULL|| lng2 IS NULL) THEN
      RETURN -1;
    END IF;
	return round(6378.138*2*asin(sqrt(pow(sin( (lat1*pi()/180-lat2*pi()/180)/2),2)+cos(lat1*pi()/180)*cos(lat2*pi()/180)* pow(sin( (lng1*pi()/180-lng2*pi()/180)/2),2)))*1000);
end $$
DELIMITER ;
