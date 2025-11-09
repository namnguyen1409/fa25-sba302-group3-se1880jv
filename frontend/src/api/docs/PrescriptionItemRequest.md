
# PrescriptionItemRequest


## Properties

Name | Type
------------ | -------------
`prescriptionId` | string
`medicineId` | string
`dosage` | string
`frequency` | string
`duration` | string
`instruction` | string
`quantity` | number

## Example

```typescript
import type { PrescriptionItemRequest } from ''

// TODO: Update the object below with actual values
const example = {
  "prescriptionId": null,
  "medicineId": null,
  "dosage": null,
  "frequency": null,
  "duration": null,
  "instruction": null,
  "quantity": null,
} satisfies PrescriptionItemRequest

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as PrescriptionItemRequest
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


