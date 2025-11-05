
# ExaminationResponse


## Properties

Name | Type
------------ | -------------
`id` | string
`patient` | [PatientResponse](PatientResponse.md)
`staff` | [StaffResponse](StaffResponse.md)
`type` | string
`status` | string
`symptom` | string
`diagnosisSummary` | string
`examinationDate` | Date
`prescription` | [PrescriptionResponse](PrescriptionResponse.md)
`serviceOrders` | [Set&lt;ServiceOrderResponse&gt;](ServiceOrderResponse.md)
`vitalSigns` | [Set&lt;VitalSignResponse&gt;](VitalSignResponse.md)
`diagnoses` | [Set&lt;DiagnosisResponse&gt;](DiagnosisResponse.md)
`labOrders` | [Set&lt;LabOrderResponse&gt;](LabOrderResponse.md)

## Example

```typescript
import type { ExaminationResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "patient": null,
  "staff": null,
  "type": null,
  "status": null,
  "symptom": null,
  "diagnosisSummary": null,
  "examinationDate": null,
  "prescription": null,
  "serviceOrders": null,
  "vitalSigns": null,
  "diagnoses": null,
  "labOrders": null,
} satisfies ExaminationResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as ExaminationResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


