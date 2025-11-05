
# MfaConfigResponse


## Properties

Name | Type
------------ | -------------
`id` | string
`createdDate` | Date
`mfaType` | string
`contact` | string
`primary` | boolean
`lastVerifiedAt` | Date
`deviceName` | string
`revoked` | boolean

## Example

```typescript
import type { MfaConfigResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "createdDate": null,
  "mfaType": null,
  "contact": null,
  "primary": null,
  "lastVerifiedAt": null,
  "deviceName": null,
  "revoked": null,
} satisfies MfaConfigResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as MfaConfigResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


