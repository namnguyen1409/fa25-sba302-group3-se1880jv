
# LabOrderResponse


## Properties

Name | Type
------------ | -------------
`id` | string
`patient` | [PatientResponse](PatientResponse.md)
`requestedBy` | [StaffResponse](StaffResponse.md)
`examinationId` | string
`status` | string
`orderCode` | string
`results` | [Set&lt;LabTestResultResponse&gt;](LabTestResultResponse.md)

## Example

```typescript
import type { LabOrderResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "patient": null,
  "requestedBy": null,
  "examinationId": null,
  "status": null,
  "orderCode": null,
  "results": null,
} satisfies LabOrderResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as LabOrderResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


