
# LabTestResultResponse


## Properties

Name | Type
------------ | -------------
`id` | string
`labTest` | [LabTestResponse](LabTestResponse.md)
`status` | string
`resultValue` | string
`unit` | string
`referenceRange` | string
`remark` | string
`verifiedBy` | [StaffResponse](StaffResponse.md)
`verifiedAt` | Date

## Example

```typescript
import type { LabTestResultResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "labTest": null,
  "status": null,
  "resultValue": null,
  "unit": null,
  "referenceRange": null,
  "remark": null,
  "verifiedBy": null,
  "verifiedAt": null,
} satisfies LabTestResultResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as LabTestResultResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


