
# PublicKeyCredentialRequestOptions


## Properties

Name | Type
------------ | -------------
`challenge` | string
`timeout` | number
`hints` | Array&lt;string&gt;
`rpId` | string
`allowCredentials` | [Array&lt;PublicKeyCredentialDescriptor&gt;](PublicKeyCredentialDescriptor.md)
`userVerification` | string
`extensions` | [AssertionExtensionInputs](AssertionExtensionInputs.md)

## Example

```typescript
import type { PublicKeyCredentialRequestOptions } from ''

// TODO: Update the object below with actual values
const example = {
  "challenge": null,
  "timeout": null,
  "hints": null,
  "rpId": null,
  "allowCredentials": null,
  "userVerification": null,
  "extensions": null,
} satisfies PublicKeyCredentialRequestOptions

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as PublicKeyCredentialRequestOptions
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


