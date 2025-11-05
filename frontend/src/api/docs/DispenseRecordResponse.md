
# DispenseRecordResponse


## Properties

Name | Type
------------ | -------------
`id` | string
`prescription` | [PrescriptionResponse](PrescriptionResponse.md)
`dispensedBy` | [StaffResponse](StaffResponse.md)
`status` | string
`dispensedAt` | Date
`totalCost` | number
`note` | string

## Example

```typescript
import type { DispenseRecordResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "prescription": null,
  "dispensedBy": null,
  "status": null,
  "dispensedAt": null,
  "totalCost": null,
  "note": null,
} satisfies DispenseRecordResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as DispenseRecordResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


