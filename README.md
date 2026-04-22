## API endpoints
API Service (порт 8080)

Добавление отзыва

POST /api/reviews

Пример запроса:

```powershell 
{
  "restaurantName": "KFC",
  "rating": 5,
  "comment": "Nice"
}
```
### Поиск отзывов
Отчёты

GET /api/search?restaurant=KFC - все отзывы о конкретном ресторане

GET /api/report/popular - самые популярные рестораны по количеству отзывов

GET /api/report/top-rated - рестораны с самыми высокими оценками

GET /api/report - все отзывы
