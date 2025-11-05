
# AllergyRequest


## Properties

Name | Type
------------ | -------------
`substance` | string
`reaction` | string
`severity` | string

## Example

```typescript
import type { AllergyRequest } from ''

// TODO: Update the object below with actual values
const example = {
  "substance": null,
  "reaction": null,
  "severity": null,
} satisfies AllergyRequest

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as AllergyRequest
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


