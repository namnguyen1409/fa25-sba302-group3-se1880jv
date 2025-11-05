
# DiagnosisResponse


## Properties

Name | Type
------------ | -------------
`id` | string
`icdCode` | string
`diseaseName` | string
`note` | string

## Example

```typescript
import type { DiagnosisResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "icdCode": null,
  "diseaseName": null,
  "note": null,
} satisfies DiagnosisResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as DiagnosisResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


