
# VitalSignRequest


## Properties

Name | Type
------------ | -------------
`examinationId` | string
`temperature` | number
`bloodPressure` | string
`pulse` | number
`respirationRate` | number
`height` | number
`weight` | number

## Example

```typescript
import type { VitalSignRequest } from ''

// TODO: Update the object below with actual values
const example = {
  "examinationId": null,
  "temperature": null,
  "bloodPressure": null,
  "pulse": null,
  "respirationRate": null,
  "height": null,
  "weight": null,
} satisfies VitalSignRequest

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as VitalSignRequest
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


