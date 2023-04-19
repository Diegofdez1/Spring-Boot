FOR /L %%x IN (1,1,10000) DO start /B curl -X POST http://localhost:8080/rest -d "name=Test Value %%x"
pause