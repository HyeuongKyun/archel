SELECT book_id, date_format(PUBLISHED_DATE, '%Y-%m-%d') as PUBLISHED_DATE
from book
where category = '인문'
and date_format(PUBLISHED_DATE, '%Y') = '2021'
order by PUBLISHED_DATE asc