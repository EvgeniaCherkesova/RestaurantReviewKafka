API endpoints
API Service (порт 8080)
Добавление отзыва
POST /api/reviews

Пример запроса:

{
  "restaurantName": "KFC",
  "rating": 5,
  "comment": "Nice"
}
Поиск отзывов
GET /api/search?restaurant=KFC
Отчеты
GET /api/report/popular
GET /api/report/top-rated
GET /api/report
