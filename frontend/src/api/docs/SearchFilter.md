
# SearchFilter


## Properties

Name | Type
------------ | -------------
`page` | number
`size` | number
`sorts` | [Array&lt;SortRequest&gt;](SortRequest.md)
`filterGroup` | [FilterGroup](FilterGroup.md)
`searchMode` | string

## Example

```typescript
import type { SearchFilter } from ''

// TODO: Update the object below with actual values
const example = {
  "page": null,
  "size": null,
  "sorts": null,
  "filterGroup": null,
  "searchMode": null,
} satisfies SearchFilter

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as SearchFilter
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


