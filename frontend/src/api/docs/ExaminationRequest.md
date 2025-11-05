
# ExaminationRequest


## Properties

Name | Type
------------ | -------------
`patientId` | string
`staffId` | string
`type` | string
`symptom` | string
`diagnosisSummary` | string

## Example

```typescript
import type { ExaminationRequest } from ''

// TODO: Update the object below with actual values
const example = {
  "patientId": null,
  "staffId": null,
  "type": null,
  "symptom": null,
  "diagnosisSummary": null,
} satisfies ExaminationRequest

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as ExaminationRequest
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


