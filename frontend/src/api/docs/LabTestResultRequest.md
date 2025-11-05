
# LabTestResultRequest


## Properties

Name | Type
------------ | -------------
`labOrderId` | string
`labTestId` | string
`status` | string
`resultValue` | string
`unit` | string
`referenceRange` | string
`remark` | string
`verifiedById` | string
`verifiedAt` | Date

## Example

```typescript
import type { LabTestResultRequest } from ''

// TODO: Update the object below with actual values
const example = {
  "labOrderId": null,
  "labTestId": null,
  "status": null,
  "resultValue": null,
  "unit": null,
  "referenceRange": null,
  "remark": null,
  "verifiedById": null,
  "verifiedAt": null,
} satisfies LabTestResultRequest

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as LabTestResultRequest
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


