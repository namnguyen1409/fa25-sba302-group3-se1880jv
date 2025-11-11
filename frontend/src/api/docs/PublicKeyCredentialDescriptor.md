
# PublicKeyCredentialDescriptor


## Properties

Name | Type
------------ | -------------
`type` | string
`id` | string
`transports` | Set&lt;string&gt;

## Example

```typescript
import type { PublicKeyCredentialDescriptor } from ''

// TODO: Update the object below with actual values
const example = {
  "type": null,
  "id": null,
  "transports": null,
} satisfies PublicKeyCredentialDescriptor

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as PublicKeyCredentialDescriptor
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


