```toml
name = 'add'
method = 'POST'
url = 'http://localhost:8080/api/produit'
sortWeight = 3000000
id = '64c7213e-92aa-4210-b4f3-2c5bbdf8f50c'

[body]
type = 'JSON'
raw = '''
{
  "nom" : "racloir",
  "prix" : 3.5,
  "vendeur": {
    "id" : 2
  },
  etiquettes : [
    { id :  1}, { i :  2}
  ]
}'''
```
