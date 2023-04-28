-- 코드를 입력하세요
SELECT car.CAR_ID, car.CAR_TYPE, FLOOR(car.DAILY_FEE * (100-discount.DISCOUNT_RATE) / 100 * 30) FEE
FROM CAR_RENTAL_COMPANY_CAR car 
JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY history 
ON car.CAR_ID = history.CAR_ID
JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN discount
ON car.CAR_TYPE = discount.CAR_TYPE
WHERE car.CAR_TYPE IN ('세단', 'SUV') AND
discount.DURATION_TYPE = '30일 이상' AND
((car.DAILY_FEE * (100-discount.DISCOUNT_RATE) * 0.01) * 30) BETWEEN 500000 AND 2000000
GROUP BY car.CAR_ID
HAVING MAX(history.END_DATE) < '2022-11-1' 
ORDER BY FEE DESC, car.CAR_TYPE, car.CAR_ID DESC;