```toml
name = 'add'
method = 'POST'
url = 'http://localhost:8080/api/produit'
sortWeight = 3000000
id = 'ee83f18d-962d-47bd-a7d3-3da3cdf0ef80'

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
    { id :  1}, { id :  2}
  ]
}'''
```
