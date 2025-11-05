
# MfaVerifyRequest


## Properties

Name | Type
------------ | -------------
`challengeId` | string
`code` | string
`deviceId` | string
`rememberMe` | boolean

## Example

```typescript
import type { MfaVerifyRequest } from ''

// TODO: Update the object below with actual values
const example = {
  "challengeId": null,
  "code": null,
  "deviceId": null,
  "rememberMe": null,
} satisfies MfaVerifyRequest

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as MfaVerifyRequest
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


