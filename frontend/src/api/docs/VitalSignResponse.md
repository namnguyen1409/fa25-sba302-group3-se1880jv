
# VitalSignResponse


## Properties

Name | Type
------------ | -------------
`id` | string
`temperature` | number
`bloodPressure` | string
`pulse` | number
`respirationRate` | number
`height` | number
`weight` | number
`createdDate` | Date

## Example

```typescript
import type { VitalSignResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "temperature": null,
  "bloodPressure": null,
  "pulse": null,
  "respirationRate": null,
  "height": null,
  "weight": null,
  "createdDate": null,
} satisfies VitalSignResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as VitalSignResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


