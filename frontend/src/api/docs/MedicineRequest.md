
# MedicineRequest


## Properties

Name | Type
------------ | -------------
`code` | string
`name` | string
`activeIngredient` | string
`dosageForm` | string
`strength` | string
`price` | number
`unit` | string
`description` | string

## Example

```typescript
import type { MedicineRequest } from ''

// TODO: Update the object below with actual values
const example = {
  "code": null,
  "name": null,
  "activeIngredient": null,
  "dosageForm": null,
  "strength": null,
  "price": null,
  "unit": null,
  "description": null,
} satisfies MedicineRequest

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as MedicineRequest
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


