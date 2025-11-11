
# RegistrationExtensionInputs


## Properties

Name | Type
------------ | -------------
`appidExclude` | [AppId](AppId.md)
`credProps` | boolean
`credentialProtectionPolicy` | string
`enforceCredentialProtectionPolicy` | boolean
`largeBlob` | [LargeBlobRegistrationInput](LargeBlobRegistrationInput.md)
`prf` | [PrfRegistrationInput](PrfRegistrationInput.md)
`uvm` | boolean

## Example

```typescript
import type { RegistrationExtensionInputs } from ''

// TODO: Update the object below with actual values
const example = {
  "appidExclude": null,
  "credProps": null,
  "credentialProtectionPolicy": null,
  "enforceCredentialProtectionPolicy": null,
  "largeBlob": null,
  "prf": null,
  "uvm": null,
} satisfies RegistrationExtensionInputs

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as RegistrationExtensionInputs
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


