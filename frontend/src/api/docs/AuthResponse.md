
# AuthResponse


## Properties

Name | Type
------------ | -------------
`requires2FA` | boolean
`mfaTypes` | Array&lt;string&gt;
`defaultMfaType` | string
`challengeId` | string
`tokenType` | string
`accessToken` | string
`expiresIn` | Date
`trustDevice` | boolean
`rememberMe` | boolean
`deviceId` | string

## Example

```typescript
import type { AuthResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "requires2FA": null,
  "mfaTypes": null,
  "defaultMfaType": null,
  "challengeId": null,
  "tokenType": null,
  "accessToken": null,
  "expiresIn": null,
  "trustDevice": null,
  "rememberMe": null,
  "deviceId": null,
} satisfies AuthResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as AuthResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


