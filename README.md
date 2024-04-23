# kotlin-api-skeleton
## 使い方
```bash
$ curl -X POST \
    -H "Content-Type:application/json" \
    http://localhost:8080/forecast \
    -d "{\"latitude\": 52.52, \"longitude\": 13.41}" \
  | jq .
  
{
  "status": "success",
  "latitude": 52.52,
  "longitude": 13.419998,
  "elevation": 38,
  "generation_time_ms": 0.04506111,
  "utc_offset_seconds": 0,
  "timezone": "GMT",
  "timezone_abbreviation": "GMT",
  ...
}
```