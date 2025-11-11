
# PrescriptionResponse


## Properties

Name | Type
------------ | -------------
`id` | string
`note` | string
`items` | [Set&lt;PrescriptionItemResponse&gt;](PrescriptionItemResponse.md)
`dispenseRecordId` | string

## Example

```typescript
import type { PrescriptionResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "note": null,
  "items": null,
  "dispenseRecordId": null,
} satisfies PrescriptionResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as PrescriptionResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


