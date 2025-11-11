
# Filter


## Properties

Name | Type
------------ | -------------
`field` | string
`fields` | Array&lt;string&gt;
`operator` | string
`value` | object
`empty` | boolean

## Example

```typescript
import type { Filter } from ''

// TODO: Update the object below with actual values
const example = {
  "field": null,
  "fields": null,
  "operator": null,
  "value": null,
  "empty": null,
} satisfies Filter

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as Filter
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


