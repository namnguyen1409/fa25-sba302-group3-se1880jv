
# FilterGroup


## Properties

Name | Type
------------ | -------------
`operator` | string
`filters` | [Array&lt;Filter&gt;](Filter.md)
`subGroups` | [Array&lt;FilterGroup&gt;](FilterGroup.md)
`empty` | boolean

## Example

```typescript
import type { FilterGroup } from ''

// TODO: Update the object below with actual values
const example = {
  "operator": null,
  "filters": null,
  "subGroups": null,
  "empty": null,
} satisfies FilterGroup

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as FilterGroup
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


