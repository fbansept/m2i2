```toml
name = 'add'
method = 'POST'
url = 'http://localhost:8080/api/etiquette'
sortWeight = 3000000
id = '199d3387-f02a-4b14-880b-1fd55cd688b1'

[body]
type = 'JSON'
raw = '''
{
  "nom" : "st valentin",
  produits : [
    { id :  1}, { id :  2}
  ]
}'''
```
